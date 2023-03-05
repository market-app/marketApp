package br.com.marketapp.product.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterJsonToString {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
