package com.example.mockdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @JsonIgnoreProperties(ignoreUnknown = true)，将这个注解写在类上之后，就会忽略类中不存在的字段，
 * 比如返回的报文中有一个birthday，而使体重没有。
 * 这个注解还可以指定要忽略的字段。使用方法如下：
 *@JsonIgnoreProperties({ "name", "address" })
 *指定的字段不会被序列化和反序列化。
 *
 * 前端的同事要求说尽量不要有null，可有为空串“” 或者 0 或者 []， 但尽量不要null。
 * @JsonInclude(Include.NON_NULL) 这个注解放在类头上就可以解决。
 * 实体类与json互转的时候 属性值为null的不参与序列化
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Game.Builder.class)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY,
        content = JsonInclude.Include.NON_NULL)
public class Game implements Serializable {
    private Integer id;
    private String name;

    public Game(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class Builder {
        private Integer id;
        private String name;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }


        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

    /**
     * 不为私有变量添加公有方法，则返回值为{} 而不是正确的json
     * @return
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
