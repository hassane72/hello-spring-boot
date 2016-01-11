package org.xiaoqiaotq.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureSource;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.shapefile.ShpFiles;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 地图文件解析类
 * Created by Caocui on 2015/8/26.
 */
public final class ShapeFileUtil {

    /**
     * return  e.g. streetcut_005002.shp
     */
    private String shapeFileName;

    public String getShapeFileName() {
        return shapeFileName;
    }

    /**
     * 读取DBF文件
     */
    public static void readDBF(String path) throws IOException {
        DbaseFileReader reader = null;
        try {
            reader = new DbaseFileReader(new ShpFiles(path), false, Charset.forName("GBK"));
            DbaseFileHeader header = reader.getHeader();
            int numFields = header.getNumFields();
            Object[] entry;
            String title;
            Object value;
            //迭代读取记录
            while (reader.hasNext()) {
                try {
                    entry = reader.readEntry();
                    for (int i = 0; i < numFields; i++) {
                        title = header.getFieldName(i);
                        value = entry[i];
                        System.out.println(title + "=" + value);
                    }
                } catch (Exception e) {
                    //错误记录 TODO
                }
            }
        } catch (Exception e) {
            //数据解析失败 TODO
        } finally {
            if (reader != null) {
                //关闭
                reader.close();
            }
        }
    }

    /**
     * 读取shp文件
     */
    public <T>  List<T> readSHP(File file,Class<T> clazz) {
        try {
            ShapefileDataStore shpDataStore = new ShapefileDataStore(file.toURI().toURL());
            shpDataStore.setStringCharset(Charset.forName("GBK"));
            String typeName = shpDataStore.getTypeNames()[0];
            FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = shpDataStore.getFeatureSource(typeName);
            FeatureCollection<SimpleFeatureType, SimpleFeature> result = featureSource.getFeatures();
            FeatureIterator<SimpleFeature> itertor = result.features();
            SimpleFeature feature;
            List<Property> properties;
            List lists=new ArrayList<>();
            Field[] fields = clazz.getDeclaredFields();
            while (itertor.hasNext()) {
                feature = itertor.next();
                properties = (List<Property>) feature.getProperties();
                T t=clazz.newInstance();
                for (int i = 0; i < properties.size(); i++) {
//                    if (pro.getValue() instanceof Point) {
//                        System.out.println("PointX = " + ((Point) (pro.getValue())).getX());
//                        System.out.println("PointY = " + ((Point) (pro.getValue())).getY());
//                    } else {
//                    }
                    Property pro = properties.get(i);
//                    System.out.println(pro.getName() + " = " + pro.getValue());
                    fields[i].setAccessible(true);
                    fields[i].set(t,pro.getValue());
                }
                lists.add(t);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    public void writeShp() {
        try {
            //定义属性
            final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                    "location:Point," + // <- the geometry attribute: Point type
                            "POIID:String," + // <- a String attribute
                            "MESHID:String," + // a number attribute
                            "OWNER:String"
            );
            SimpleFeatureCollection collection = FeatureCollections.newCollection();
            GeometryFactory geometryFactory = new GeometryFactory();
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);

            double latitude = Double.parseDouble("116.123456789");
            double longitude = Double.parseDouble("39.120001");
            String POIID = "2050003092";
            String MESHID = "0";
            String OWNER = "340881";

            Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
            Object[] obj = {point, POIID, MESHID, OWNER};
            SimpleFeature feature = featureBuilder.buildFeature(null, obj);
            collection.add(feature);
            feature = featureBuilder.buildFeature(null, obj);
            collection.add(feature);

            File newFile = new File("D:/newPoi.shp");
            ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
            Map<String, Serializable> params = new HashMap<>();
            params.put("url", newFile.toURI().toURL());
            params.put("create spatial index", Boolean.TRUE);
            ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
            newDataStore.createSchema(TYPE);
            newDataStore.forceSchemaCRS(DefaultGeographicCRS.WGS84);

            Transaction transaction = new DefaultTransaction("create");
            String typeName = newDataStore.getTypeNames()[0];
            SimpleFeatureSource featureSource = newDataStore.getFeatureSource(typeName);
            if (featureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.addFeatures(collection);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(typeName + " does not support read/write access");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写shape文件，以point为例
     */
//    public static void main(String[] args) throws Exception {
//
//
//        /*****************************数据输出测试方法*************************/
//
//
//        /*************************************shp数据读取********************************/
////        readSHP("C:\\Users\\Caocui\\Desktop\\BD\\DD\\dandong.shp");
//
//        /*************************************dbf数据读取********************************/
////        readDBF("C:\\Users\\Caocui\\Desktop\\BD\\DD\\dandong.dbf");
////        List list = readSHP(new File("C:\\Users\\wang\\Documents\\Tencent Files\\843036893\\FileRecv\\迪卡侬模板数据20151014\\街道\\街道导出\\streetcut_002001.shp1"));
//        ShapeFileUtil shapeFileUtil=new ShapeFileUtil();
//        String zipname = "C:\\Users\\wang\\Documents\\Tencent Files\\843036893\\FileRecv\\迪卡侬模板数据20151014\\街道\\街道导出\\streetExport.zip";
//        FileInputStream in = new FileInputStream(zipname);
//        List list = shapeFileUtil.importSpatialStream(in, StreetTest.class);
//        System.err.println(list);
//    }

    /**
     * 导入空间数据
     * @param in   默认导入的是zip文件流
     * @param clazz
     * @return
     * @throws Exception
     */
    public <T> List<T> importSpatialStream(InputStream in,Class<T> clazz)  {
        List list=null;
        try {
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(in));
            ZipEntry entry;
            File shpFile=null;
            List<File> tempFiles = new ArrayList<>();
            //getNextEntry()！=null时，ZipInputStream读取每个压缩文件，
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Unzipping: " + entry.getName());
                int size;
                byte[] buffer = new byte[2048];
                File temp = new File(System.getProperty("java.io.tmpdir"),entry.getName());
                tempFiles.add(temp);
                FileOutputStream fos = new FileOutputStream(temp);
                System.err.println(temp.toURI());
                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);
                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, size);
                }
                String fileName = entry.getName().toLowerCase();
                if (fileName.endsWith(".shp")) {//读取shp文件
                   shpFile=temp;
                    this.shapeFileName=fileName;
                }
                bos.flush();
                bos.close();
            }
            list = readSHP(shpFile,clazz);
            //删除上传文件
            tempFiles.forEach(f->f.delete());
            zis.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
