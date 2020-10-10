package com.spring.component.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xuweizhi
 */
public class ToLongCollectionDeserializer extends StdDeserializer<Collection<Long>> {
    public ToLongCollectionDeserializer() {
        super(TypeFactory.defaultInstance().constructCollectionLikeType(Collection.class, Long.class));
    }

    @Override
    public Collection<Long> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentTokenId() == JsonTokenId.ID_STRING) {
            return JSON.fromJson(p.getText(), new TypeReference<List<Long>>() {
            });
        } else if (p.isExpectedStartArrayToken()) {
            ArrayNode an = deserializeArray(p, ctxt, ctxt.getNodeFactory());
            Collection<Long> col = new ArrayList<>();
            an.forEach(a -> col.add(a.asLong()));
            return col;
        } else {
            return (Collection<Long>) ctxt.handleUnexpectedToken(Collection.class, p);
        }
    }

    private ArrayNode deserializeArray(JsonParser p, DeserializationContext ctxt, final JsonNodeFactory nodeFactory)
            throws IOException {
        ArrayNode node = nodeFactory.arrayNode();
        while (true) {
            JsonToken t = p.nextToken();
            switch (t.id()) {
                case JsonTokenId.ID_START_ARRAY:
                    break;
                case JsonTokenId.ID_END_ARRAY:
                    return node;
                case JsonTokenId.ID_STRING:
                    node.add(nodeFactory.textNode(p.getText()));
                    break;
                case JsonTokenId.ID_NUMBER_INT:
                    node.add(fromInt(p, ctxt, nodeFactory));
                    break;
                case JsonTokenId.ID_NULL:
                    node.add(nodeFactory.nullNode());
                    break;
                default:
                    throw new IOException("parse type error..");
            }
        }
    }

    private JsonNode fromInt(JsonParser p, DeserializationContext ctxt, JsonNodeFactory nodeFactory)
            throws IOException {
        JsonParser.NumberType nt;
        int feats = ctxt.getDeserializationFeatures();
        if ((feats & F_MASK_INT_COERCIONS) != 0) {
            if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(feats)) {
                nt = JsonParser.NumberType.BIG_INTEGER;
            } else if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(feats)) {
                nt = JsonParser.NumberType.LONG;
            } else {
                nt = p.getNumberType();
            }
        } else {
            nt = p.getNumberType();
        }
        if (nt == JsonParser.NumberType.INT) {
            return nodeFactory.numberNode(p.getIntValue());
        }
        if (nt == JsonParser.NumberType.LONG) {
            return nodeFactory.numberNode(p.getLongValue());
        }
        return nodeFactory.numberNode(p.getBigIntegerValue());
    }

}
