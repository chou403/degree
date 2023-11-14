package com.second.common.util;

import com.second.common.bean.domain.TempDataDTO;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/21
 * {@code @description} stram 相关 utils 示例
 */
public class StreamUtils {

    /**
     * stream distinctByKey Multiple deduplication parameters can be set
     * e.g
     * 1.List<RepairLabourDTO> installList = accessoryList.stream()
     * .filter(distinctByKey(item -> Stream.of(item.getSubOrderNo(), item.getMaterialCode()).toArray()))
     * .collect(Collectors.toList());
     * <p>
     * 2.List<Map> vehicleList = repairList
     * .stream()
     * .filter(CommonUtil.distinctByKey(item -> item.get("vin")))
     * .collect(Collectors.toList());
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        ConcurrentSkipListMap<Object, Boolean> skipListMap = new ConcurrentSkipListMap<>();

        return t -> skipListMap.putIfAbsent(JsonHelper.parseToJson(keyExtractor.apply(t)), Boolean.TRUE) == null;
    }

    /**
     * sum list param to Integer
     */
    public static Integer listSumInteger(List<TempDataDTO> tempList) {
        return tempList
                .stream()
                .map(TempDataDTO::getTempDataInteger)
                .reduce(0, Integer::sum);
    }

    /**
     * sum list param to BigDecimal
     */
    public static BigDecimal listSumBigDecimal(List<TempDataDTO> tempList) {
        return tempList
                .stream()
                .map(TempDataDTO::getTempDataBigDeciaml)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * list convert to map by paramA as key , paramB sum as value
     */
    public static Map<String, Integer> listToMapIntegerSumByParam(List<TempDataDTO> tempList) {
        return tempList
                .stream()
                .collect(Collectors.groupingBy(TempDataDTO::getTempDataStringA,
                        Collectors.mapping(TempDataDTO::getTempDataInteger,
                                Collectors.reducing(0, Integer::sum))));
    }

    /**
     * map convert to list , sort by value reverse
     */
    public static List<TempDataDTO> mapToListReverseSortByValue(Map<String, Integer> tempMap) {
        return tempMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(item -> {
                    TempDataDTO dto = new TempDataDTO();
                    dto.setTempDataStringA(item.getKey());
                    dto.setTempDataInteger(item.getValue());
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * map convert to list , sort by key reverse
     */
    public static List<TempDataDTO> mapToListReverseSortByKey(Map<String, Integer> tempMap) {
        return tempMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .map(item -> {
                    TempDataDTO dto = new TempDataDTO();
                    dto.setTempDataStringA(item.getKey());
                    dto.setTempDataInteger(item.getValue());
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * map convert to list , sort by value
     */
    public static List<TempDataDTO> mapToListSortByValue(Map<String, Integer> tempMap) {
        return tempMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(item -> {
                    TempDataDTO dto = new TempDataDTO();
                    dto.setTempDataStringA(item.getKey());
                    dto.setTempDataInteger(item.getValue());
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * map convert to list , sort by key
     */
    public static List<TempDataDTO> mapToListSortByKey(Map<String, Integer> tempMap) {
        return tempMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(item -> {
                    TempDataDTO dto = new TempDataDTO();
                    dto.setTempDataStringA(item.getKey());
                    dto.setTempDataInteger(item.getValue());
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * concat two list
     */
    public static List<Map> concatList(List<Map> tempList1, List<Map> tempList2) {
        return Stream
                .concat(tempList1.stream(), tempList2.stream())
                .collect(Collectors.toList());
    }

    /**
     * list to map group one param
     */
    public static Map<String, List<TempDataDTO>> listToMapGroupByVale(List<TempDataDTO> tempList) {
        return tempList
                .stream()
                .collect(Collectors.groupingBy(TempDataDTO::getTempDataStringA));
    }

    /**
     * distinct list by two param
     */
    public static List<TempDataDTO> distinctByTwoParamList(List<TempDataDTO> tempList) {
        return tempList
                .stream()
                .filter(distinctByKey(item -> Stream.of(item.getTempDataStringA(), item.getTempDataStrinB()).toArray()))
                .collect(Collectors.toList());
    }

    /**
     * list to map by param
     */
    public static Map<String, TempDataDTO> listToMapByParam(List<TempDataDTO> tempList) {
        return tempList
                .stream()
                .collect(Collectors.toMap(TempDataDTO::getTempDataStringA, item -> item));
    }
}
