package usermanager.domain;

import lombok.Data;

@Data
public class User {
		private int id;
		private String name;
		private int age;
		private String image="/images/default.jpg";
}
