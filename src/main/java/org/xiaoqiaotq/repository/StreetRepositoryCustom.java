package org.xiaoqiaotq.repository;

import org.xiaoqiaotq.domain.geom.StreetTest;

import java.util.List;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/15
 */
public interface StreetRepositoryCustom {
    /**
     * 导入街道数据
     * @param streetList
     * @param tblName  e.g. streetcut_005002
     */
    void importStreet(List<StreetTest> streetList, String tblName);

    /**
     * 根据表格名字查询街道
     * @param tblName
     * @return
     */
    List<StreetTest> findStreetByTblName(String tblName);
}
