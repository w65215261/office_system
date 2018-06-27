package com.pmcc.soft.core.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunyake on 15/7/10.
 */
public class CommonTimeFormat extends JsonSerializer<Date> {

    @Override
    public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatDate = sdf.format(arg0);
        arg1.writeString(formatDate);
    }

}
