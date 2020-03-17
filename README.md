采用 springBoot默认提供的redis，存储set("redis_key","redis_value")
结果在redis manager可视化工具中查看，发现是：\xAC\xED\x00\x05t\x00\x09redis_key   \xAC\xED\x00\x05t\x00\x0Bredis_value

原因：当数据存储到redis中时候key和value都是通过spring serializer进行序列化的，
RedisTemplate, spring默认会使用jdk序列化，如果使用jdk序列化，model模型必须实现Serializable且要有一个空的构造器，

序列化接口：
RedisSerializer 

实现类
GenericToStringSerializer：使用Spring转换服务进行序列化；
JacksonJsonRedisSerializer：使用Jackson 1，将对象序列化为JSON；
Jackson2JsonRedisSerializer：使用Jackson 2，将对象序列化为JSON；
JdkSerializationRedisSerializer：使用Java序列化；
OxmSerializer ：
使用Spring O/X映射的编排器和解排器（marshaler和unmarshaler）实现序列化，
用于XML序列化

StringRedisSerializer：序列化String类型的key和value。实际上是String和byte数组之间的转换