package org.xiaoqiaotq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaoqiaotq.domain.geom.StreetTest;
import org.xiaoqiaotq.repository.StreetRepository;
import org.xiaoqiaotq.util.ShapeFileUtil;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.List;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/16
 */
@Service
public class StreetService {
    @Autowired
    StreetRepository streetRepository;

    @Transactional
    public void importFile(InputStream in){
        ShapeFileUtil shapeFileUtil=new ShapeFileUtil();
        List list = shapeFileUtil.importSpatialStream(in, StreetTest.class);
        String shapeFileName = shapeFileUtil.getShapeFileName();
        String tblName = shapeFileName.substring(0, shapeFileName.indexOf("."));
        streetRepository.importStreet(list, tblName);
    }
    public List<StreetTest> queryStreet(String cityCode){
        String tblName = "streetcut_" + cityCode;
        return streetRepository.findStreetByTblName(tblName);
    }
}
