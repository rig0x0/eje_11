package com.upiiz.securityInDataBase;

import com.upiiz.securityInDataBase.entities.PermissionEntity;
import com.upiiz.securityInDataBase.entities.RolEntity;
import com.upiiz.securityInDataBase.entities.RolEnum;
import com.upiiz.securityInDataBase.entities.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class SecurityInDataBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityInDataBaseApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			// Crear permisos
			PermissionEntity createPermission = PermissionEntity
					.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity
					.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity
					.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity
					.builder()
					.name("DELETE")
					.build();

			PermissionEntity deployPermission = PermissionEntity
					.builder()
					.name("DEPLOY")
					.build();

			// Crear roles
			RolEntity adminRol = RolEntity
					.builder()
					.rolEnum(RolEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RolEntity guestRol = RolEntity
					.builder()
					.rolEnum(RolEnum.GUEST)
					.permissions(Set.of(readPermission))
					.build();

			RolEntity userRol = RolEntity
					.builder()
					.rolEnum(RolEnum.USER)
					.permissions(Set.of(readPermission, updatePermission))
					.build();

			RolEntity developerRol = RolEntity
					.builder()
					.rolEnum(RolEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, deployPermission))
					.build();

			// Crear usuarios
			UserEntity user1 = UserEntity
					.builder()
					.username("miguel")
					.password("1234")
					.isEnable(true)
					.isAccountNonExpired(true)
					.isCredentialsNonExpired(true)
					.isAccountNonLocked(true)
					.roles(Set.of(developerRol))
					.build();

			UserEntity user2 = UserEntity
					.builder()
					.username("alonso")
					.password("1234")
					.isEnable(true)
					.isAccountNonExpired(true)
					.isCredentialsNonExpired(true)
					.isAccountNonLocked(true)
					.roles(Set.of(userRol))
					.build();

			UserEntity user3 = UserEntity
					.builder()
					.username("felipe")
					.password("1234")
					.isEnable(true)
					.isAccountNonExpired(true)
					.isCredentialsNonExpired(true)
					.isAccountNonLocked(true)
					.roles(Set.of(guestRol))
					.build();

			UserEntity user4 = UserEntity
					.builder()
					.username("admin")
					.password("1234")
					.isEnable(true)
					.isAccountNonExpired(true)
					.isCredentialsNonExpired(true)
					.isAccountNonLocked(true)
					.roles(Set.of(adminRol))
					.build();

		};
	}

}
