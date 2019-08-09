package com.kobylynskyi.graphql.codegen.mapper;

import com.kobylynskyi.graphql.codegen.model.MappingConfig;
import com.kobylynskyi.graphql.codegen.utils.Utils;
import graphql.language.InterfaceTypeDefinition;

import java.util.HashMap;
import java.util.Map;

import static com.kobylynskyi.graphql.codegen.model.DataModelFields.*;

/**
 * Map interface definition to a Freemarker data model
 *
 * @author kobylynskyi
 */
public class InterfaceDefinitionToDataModelMapper {

    /**
     * Map interface definition to a Freemarker data model
     *
     * @param mappingConfig  Global mapping configuration
     * @param typeDefinition GraphQL interface definition
     * @return Freemarker data model of the GraphQL interface
     */
    public static Map<String, Object> map(MappingConfig mappingConfig, InterfaceTypeDefinition typeDefinition) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put(PACKAGE, mappingConfig.getJavaPackage());
        dataModel.put(CLASS_NAME, Utils.capitalize(typeDefinition.getName()));
        dataModel.put(FIELDS, FieldDefinitionToParameterMapper.map(mappingConfig, typeDefinition.getFieldDefinitions()));
        return dataModel;
    }

}