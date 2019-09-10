package com.example.think.test1.utils.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author 406
 * @version 1.0.0
 * @ClassName CustomGsonResponseBodyConverter
 * @Description
 * @E-mail
 * @time 2018/03/19
 */
final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

//    @Override public T convert(ResponseBody value) throws IOException {
//        String response = value.string();
//        HttpStatus httpStatus = gson.fromJson(response, HttpStatus.class);
//        if (httpStatus.isCodeInvalid()) {
//            value.close();
//            throw new ApiException(httpStatus.getError_code(), httpStatus.getReason());
//        }
//
//        MediaType contentType = value.contentType();
//        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
//        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
//        Reader reader = new InputStreamReader(inputStream, charset);
//        JsonReader jsonReader = gson.newJsonReader(reader);
//
//        try {
//            return adapter.read(jsonReader);
//        } finally {
//            value.close();
//        }
//    }

    @Override public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }

}
