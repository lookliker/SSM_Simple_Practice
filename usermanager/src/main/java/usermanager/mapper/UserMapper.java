package usermanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import usermanager.domain.User;


public interface UserMapper {
	User get(int id);

	List<User> getAll();

	void insert(User u);

	void delete(int id);

	void update(@Param("u") User u, @Param("id") int id);

	void updateImage(@Param("image") String image, @Param("id") int id);
}
