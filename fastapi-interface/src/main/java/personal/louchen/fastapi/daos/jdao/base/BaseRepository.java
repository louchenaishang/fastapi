package personal.louchen.fastapi.daos.jdao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Serializable> {

    /**
     * 所有实体对应的dao继承这个接口,可以随时替换该接口继承的jpa接口
     */

      /*
    一些jpa使用的例子
    And --- 等价于 SQL 中的 and 关键字，比如 findByUsernameAndPassword(String user, Striang pwd)；
    Or --- 等价于 SQL 中的 or 关键字，比如 findByUsernameOrAddress(String user, String addr)；
    Between --- 等价于 SQL 中的 between 关键字，比如 findBySalaryBetween(int max, int min)；
    LessThan --- 等价于 SQL 中的 "<"，比如 findBySalaryLessThan(int max)；
    GreaterThan --- 等价于 SQL 中的">"，比如 findBySalaryGreaterThan(int min)；
    IsNull --- 等价于 SQL 中的 "is null"，比如 findByUsernameIsNull()；
    IsNotNull --- 等价于 SQL 中的 "is not null"，比如 findByUsernameIsNotNull()；
    NotNull --- 与 IsNotNull 等价；
    Like --- 等价于 SQL 中的 "like"，比如 findByUsernameLike(String user)；
    NotLike --- 等价于 SQL 中的 "not like"，比如 findByUsernameNotLike(String user)；
    OrderBy --- 等价于 SQL 中的 "order by"，比如 findByUsernameOrderBySalaryAsc(String user)；
    Not --- 等价于 SQL 中的 "！ ="，比如 findByUsernameNot(String user)；
    In --- 等价于 SQL 中的 "in"，比如 findByUsernameIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；
    NotIn --- 等价于 SQL 中的 "not in"，比如 findByUsernameNotIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；

    jpa自动实现 接口的实现代码,只需要定义好接口直接调用
    TSysUser findByPhone(String phone);

    @Query("select a from ForumThread a where "
			+ "a.title = ?1 "
			+ "order by a.addtime desc")
	List<ForumThread> selectByTitle(String title);

	@Query("select a from ForumThread a "
			+ "left join a.forumTopic"
			+ " where "
			+ "a.forumTopic.topicId = ?1 "
			+ "order by a.addtime")
	List<ForumThread> selectByTopicId(Integer id);

	@Query("select a from ForumThread a "
			+ "left join a.forumTopic"
			+ " where "
			+ "a.forumTopic.topicId = ?1 "
			+ "order by a.addtime")
	Page<ForumThread> selectByTopicIdListPage(Integer id,Pageable pageable);

	@Query("select count(a) from ForumThread a "
			+ "left join a.forumTopic"
			+ " where "
			+ "a.forumTopic.topicId = ?1 ")
	Long CountByTopicId(Integer id);

     */

}
