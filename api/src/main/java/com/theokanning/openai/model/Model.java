package com.theokanning.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * GPT model details
 *
 * https://beta.openai.com/docs/api-reference/models
 */
@Data
public class Model {
    /**
     * An identifier for this model, used to specify the model when making completions, etc
     * 一个模型的标识符：
     */
    public String id;

    /**
     * The type of object returned, should be "model"
     * 模型返回的类型
     */
    public String object;

    /**
     * The owner of the model, typically "openai"
     * 模型的拥有者
     */
    @JsonProperty("owned_by")
    public String ownedBy;

    /**
     * List of permissions for this model
     * 模型的权限列表
     */
    public List<Permission> permission;

    /**
     * The root model that this and its parent (if applicable) are based on
     * 根模型
     */
    public String root;

    /**
     * The parent model that this is based on
     * 父模型
     */
    public String parent;
}
