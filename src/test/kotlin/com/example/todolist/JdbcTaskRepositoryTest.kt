package com.example.todolist

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner

@RunWith(value = SpringRunner::class)
@SpringBootTest
@Sql(statements = ["DELETE FROM task"])
class JdbcTaskRepositoryTest {

    @Autowired
    // lateinit をつけるとプロパティ宣言時の初期化を回避することができる
    private lateinit var sut: JdbcTaskRepository

    @Test
    fun 何も作成しなければfindAllはからのリストを返すこと () {
        val got = sut.findAll()
        assertThat(got).isEmpty()
    }

    @Test
    fun createで作成したタスクをfindByIdで取得できること() {
        val task = sut.create("TEST")
        val got = sut.findById(task.id)
        assertThat(got).isEqualTo(task)

    }
}