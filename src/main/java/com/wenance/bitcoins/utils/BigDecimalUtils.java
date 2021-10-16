package com.wenance.bitcoins.utils;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@NoArgsConstructor
public class BigDecimalUtils {

    public BigDecimal getAverage(List<BigDecimal> listPrice){
        boolean exist= listPrice.stream().mapToDouble(BigDecimal::doubleValue).average().isPresent();
        if(exist){
            return BigDecimal.valueOf(listPrice.stream().mapToDouble(BigDecimal::doubleValue).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP);
        }
        else return BigDecimal.valueOf(0.0);
    }

    public BigDecimal getPercentageDifference(List<BigDecimal> listPrice){
        BigDecimal averageValue=getAverage(listPrice);
        BigDecimal max=BigDecimal.valueOf(0.0);
        boolean exist= listPrice.stream().mapToDouble(BigDecimal::doubleValue).max().isPresent();
        if(exist){
            max =  BigDecimal.valueOf(listPrice.stream().mapToDouble(BigDecimal::doubleValue).max().getAsDouble());
        }else return BigDecimal.ZERO;
        return max.subtract(averageValue).divide(BigDecimal.valueOf(100.0)).setScale(2, RoundingMode.HALF_UP);
    }
}
