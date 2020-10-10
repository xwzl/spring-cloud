package com.spring.component.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.Collection;

/**
 * @author xuweizhi
 */
public class CollectionToStringSerializer extends StdSerializer<Collection<? extends Object>> {

    public CollectionToStringSerializer() {
        super(TypeFactory.defaultInstance().constructCollectionLikeType(Collection.class, Object.class));
    }

    @Override
    public void serialize(Collection<? extends Object> value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
        // List<String> list = value.stream().collect(ArrayList::new, (a, b) -> a.add(b.toString()), ArrayList::addAll);
        gen.writeStartArray();
        for (Object o : value) {
            gen.writeString(o.toString());
        }
        gen.writeEndArray();
    }

}
