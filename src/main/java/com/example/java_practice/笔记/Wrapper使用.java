/*
Wrapper 主要用于构建SQL中的where条件
主要有三类
类型	用途	是否推荐
QueryWrapper	普通写法，字段用字符串
LambdaQueryWrapper	用方法引用写字段
UpdateWrapper	构建更新条件
一般用LambdaQueryWrapper

创建方法：
LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
使用示例：
LambdaQueryWrapper<CourseStudent> wrapper = new LambdaQueryWrapper<>();
wrapper.eq(CourseStudent::getUserId, userId)
       .eq(CourseStudent::getCourseId, courseId);

CourseStudent cs = courseStudentMapper.selectOne(wrapper);

SQL                                     java
WHERE id = 1                            wrapper.eq(User::getId, 1);
WHERE id != 1                           wrapper.ne(User::getRole, 1);
WHERE id > 1                            wrapper.gt(User::getAge, 1);
WHERE id >= 1                           wrapper.ge(User::getAge, 18);
WHERE id <                              wrapper.lt(User::getAge, 60);
WHERE id <= 1                           wrapper.le(User::getAge, 60);
WHERE username LIKE '%张%'              wrapper.like(User::getUsername, "张");
WHERE role = 1 AND age > 18             wrapper.eq(User::getRole, 1)
                                                .gt(User::getAge, 18);
WHERE role = 1 OR age > 18              wrapper.eq(User::getRole, 1)
                                               .or()
                                               .eq(User::getRole, 2);
WHERE id in (1,2,3)                     wrapper.in(User::getId, Arrays.asList(1L, 2L, 3L));


 */