package com.example.think.test1.utils.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * @author 406
 * @version 1.0.0
 * @ClassName CustomGsonRequestBodyConverter
 * @Description
 * @E-mail
 * @time 2018/03/19
 */
final class CustomGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
//        TLog.e();
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public RequestBody convert(T value) throws IOException {
//        TLog.e();
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }


}
