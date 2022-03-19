package com.demo.h2.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Data
public class User {

    /**
     * 注意：
     * 这里的@GeneratedValue注解会使用一个叫idGenerator的生成器，
     * 而这个叫idGenerator的生成器在@GenericGenerator里定义的，
     * 该生成器的生成策略为：identity，这种策略可以支持"主键自增长"，
     * 因此我们在创建User的时候可以不用指定id
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "identity")
    @GeneratedValue(generator = "idGenerator")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "email", length = 64)
    private String email;
}