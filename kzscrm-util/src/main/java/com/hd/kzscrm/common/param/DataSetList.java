package com.hd.kzscrm.common.param;

import com.hd.kzscrm.common.enums.DataListSetEnum;

import java.util.ArrayList;

/**
 * @author  lichangchao
 *
 */
public class DataSetList<E extends DataListSetParam> extends ArrayList<E> {

    public   DataSetList<E>  addE(DataListSetEnum dataListSetEnum,String inName,String outName){
        DataListSetParam  param = new  DataListSetParam();
        param.setDataListSetEnum(dataListSetEnum);
        param.setInName(inName);
        param.setOutName(outName);
        this.add((E) param);
        return  this;
    }

  public static void main(String arge[]){
      DataSetList<DataListSetParam> dataSetList = new DataSetList();
      dataSetList.addE(DataListSetEnum.CMS_CATEGORTY,"cate","").addE(DataListSetEnum.COMPANY,"ASDF","ASDF");
      for(DataListSetParam param:dataSetList){
          System.out.println(param.getOutName());
      }
  }

}
