package com.ming.shopping.product.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessResourceFailureException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> getJsonData(Class<T> elementClazz, String path) {
        Resource resource = new ClassPathResource(path);
        try (InputStream is = resource.getInputStream()) {
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClazz);
            return mapper.readValue(is, javaType);
        } catch (IOException e) {
            throw new DataAccessResourceFailureException("Json stream IO error occurs", e);
        }
    }
}
