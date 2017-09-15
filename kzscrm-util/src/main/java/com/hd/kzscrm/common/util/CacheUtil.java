package com.hd.kzscrm.common.util;

/**
 * Lg-CENTER缓存共通方法工具类
 * Created by liuming on 2016/7/18.
 */
public class CacheUtil {

    /**
     * 获取数据选项码表中的相应分类数据
     *
     * @param classify 数据分类
     * @return
     */
//    public static List<BaseDataOptionPO> getDataBaseOptionByClassify(String classify) {
//
//        // 根据缓存ID取数据字典数据
//        List<BaseDataOptionPO> sdList = DBEntityCacheHelper.getList(Consts.SYS_KEY_MAIN,
//                Consts.BASE_DATA_OPTION, BaseDataOptionPO.class, null, 30 * 3600);
//
//        List<BaseDataOptionPO> retList = new ArrayList<>();
//
//        for (BaseDataOptionPO sdp : sdList) {
//            if (StringUtils.isEquals(sdp.getDeleteFlag(), "0")&& StringUtils.isEquals(sdp.getClassifyCode(), classify)) {
//                retList.add(sdp);
//            }
//        }
//
//        return retList;
//    }
//
//
//    /**
//     * 获取具体名称
//     *
//     * @param optionCode
//     * @param classify 数据分类
//     * @return
//     */
//    public static String getDataBaseOptionName(String optionCode ,String classify) {
//
//        // 根据缓存ID取数据字典数据
//        List<BaseDataOptionPO> retList = getDataBaseOptionByClassify(classify);
//
//        if (CollectionUtils.isEmpty(retList)) {
//            return null;
//        }
//
//        for (BaseDataOptionPO sdp : retList) {
//            if (StringUtils.isEquals(sdp.getOptionCode(), optionCode)) {
//                return sdp.getOptionName();
//            }
//        }
//
//        return null;
//    }

}
