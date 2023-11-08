package com.example.skeleton.global.config.redis;

import com.example.skeleton.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.Set;

class RedisTemplateTest extends IntegrationTest {

    // reference : https://bcp0109.tistory.com/328

    @Autowired
    private RedisTemplate<String, String> redisTemplate; // RedisTemplate, StringRedisTemplate class 존재

    /** Stirngs 자료 구조에 대한 실행 테스트
    * */
    @Test
    void testStrings() {
        // given
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "stringKey";
        String value = "testStrings";

        // when
        valueOperations.set(key, value);

        // then
        String storedValue = valueOperations.get(key);
        Assertions.assertThat(storedValue).isEqualTo(value);

        redisTemplate.delete(key);
    }

    /** Set 자료 구조에 대한 실행 테스트
     * */
    @Test
    void testSet() {
        // given
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        String key = "setKey";

        // when
        setOperations.add(key, "t", "e", "s", "t", "s", "e", "t");

        // then
        Set<String> members = setOperations.members(key);
        Long size = setOperations.size(key);

        Assertions.assertThat(members).containsOnly("t", "e", "s"); // containsOnly : 포함 여부 확인, 순서 상관 없음
        Assertions.assertThat(size).isEqualTo(3L);

        redisTemplate.delete(key);
    }

    /** Hash 자료 구조에 대한 실행 테스트
     * */
    @Test
    void testHash() {
        // given
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String key = "hashKey";

        // when
        hashOperations.put(key, "test", "hash");

        // then
        Object value = hashOperations.get(key, "test");
        Assertions.assertThat(value).isEqualTo("hash");

        Map<Object, Object> entries = hashOperations.entries(key); // entires method : Get entire hash stored at key.
        Assertions.assertThat(entries.keySet()).containsExactly("test"); // containsExactly : 순서대로 포함 여부 확인
        Assertions.assertThat(entries.values()).containsExactly("hash");

        Long size = hashOperations.size(key); // size method : Get size of hash at key.
        Assertions.assertThat(size).isEqualTo(entries.size());

        redisTemplate.delete(key);
    }
}