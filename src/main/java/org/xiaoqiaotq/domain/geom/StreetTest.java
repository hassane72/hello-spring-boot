package org.xiaoqiaotq.domain.geom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.MultiPolygon;
import org.hibernate.annotations.Type;
import org.xiaoqiaotq.domain.BaseEntity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * 街道
 * Created by Caocui on 2015/8/20.
 *
 *    streetcut_002001.shp 文件
 *
 *    the_geom = MULTIPOLYGON (((116.28920400000005 39.89559100000008, 116.28918899999996 39.89489900000001,)))
     编码 = 002001006008
     中文名称 = 卢沟桥街道
     英文名称 =
     别称 =
     上级编码 = 002001006
     省 = 北京
     市 = 北京
     区 = 丰台区
     常住人口 = 181666
     人口年份 = 2011
     出图编号 = 133
 */
@Entity
public class StreetTest extends BaseEntity implements Serializable {

    @JsonIgnore
    @Type(type = "org.hibernate.spatial.GeometryType")
    private MultiPolygon geom;//空间数据信息

    private String code;//NTT编码

    private String nameCn;//中文名称

    private String nameEn;//英文名称

    private String aliasName;//别称

    private String parentCode;//上级编码

    private String province;  //省

    private String city;       //市

    private String district;   //区

    private int population;    //人口

    private int populationYear;//人口年份

    private String figureNumber; //出图编号


    public MultiPolygon getGeom() {
        return geom;
    }

    public void setGeom(MultiPolygon geom) {
        this.geom = geom;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulationYear() {
        return populationYear;
    }

    public void setPopulationYear(int populationYear) {
        this.populationYear = populationYear;
    }

    public String getFigureNumber() {
        return figureNumber;
    }

    public void setFigureNumber(String figureNumber) {
        this.figureNumber = figureNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

}
