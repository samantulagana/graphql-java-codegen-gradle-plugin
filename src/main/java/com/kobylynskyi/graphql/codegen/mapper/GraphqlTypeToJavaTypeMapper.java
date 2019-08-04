package com.kobylynskyi.graphql.codegen.mapper;

import com.kobylynskyi.graphql.codegen.model.MappingConfig;
import graphql.language.ListType;
import graphql.language.NonNullType;
import graphql.language.Type;
import graphql.language.TypeName;

import java.util.Map;

/**
 * Map GraphQL type to Java type
 *
 * @author kobylynskyi
 */
public class GraphqlTypeToJavaTypeMapper {

    static String mapToJavaType(MappingConfig mappingConfig, Type type) {
        if (type instanceof TypeName) {
            return mapToJavaType(mappingConfig, ((TypeName) type).getName());
        } else if (type instanceof ListType) {
            return wrapIntoJavaCollection(mapToJavaType(mappingConfig, ((ListType) type).getType()));
        } else if (type instanceof NonNullType) {
            return mapToJavaType(mappingConfig, ((NonNullType) type).getType());
        }
        return null;
    }

    private static String wrapIntoJavaCollection(String type) {
        return String.format("java.util.Collection<%s>", type);
    }

    private static String mapToJavaType(MappingConfig mappingConfig, String graphlType) {
        Map<String, String> graphqlScalarsMapping = mappingConfig.getCustomTypesMapping();
        if (graphqlScalarsMapping.containsKey(graphlType)) {
            return graphqlScalarsMapping.get(graphlType);
        }
        switch (graphlType) {
            case "ID":
                return "String";
            case "Int":
                return "Integer";
            default: // String, Float, Boolean
                return graphlType;
        }
    }

}