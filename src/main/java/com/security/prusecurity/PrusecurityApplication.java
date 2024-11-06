package com.security.prusecurity;

import com.security.prusecurity.persistence.entites.PermisionEntity;
import com.security.prusecurity.persistence.entites.RoleEntity;
import com.security.prusecurity.persistence.entites.RoleEnum;
import com.security.prusecurity.persistence.entites.UserEntity;
import com.security.prusecurity.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.List;
import java.util.Set;


@SpringBootApplication
public class PrusecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrusecurityApplication.class, args);}

		@Bean
		CommandLineRunner init(UserRepository userRepository) {
			return args -> {
				/* Create PERMISSIONS */
				PermisionEntity createPermission = PermisionEntity.builder()
						.name("CREATE")
						.build();

				PermisionEntity readPermission = PermisionEntity.builder()
						.name("READ")
						.build();

				PermisionEntity updatePermission = PermisionEntity.builder()
						.name("UPDATE")
						.build();

				PermisionEntity deletePermission = PermisionEntity.builder()
						.name("DELETE")
						.build();

				PermisionEntity refactorPermission = PermisionEntity.builder()
						.name("REFACTOR")
						.build();

				/* Create ROLES */
				RoleEntity roleAdmin = RoleEntity.builder()
						.roleEnum(RoleEnum.ADMIN)
						.permisionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
						.build();

				RoleEntity roleUser = RoleEntity.builder()
						.roleEnum(RoleEnum.USER)
						.permisionList(Set.of(createPermission, readPermission))
						.build();

				RoleEntity roleInvited = RoleEntity.builder()
						.roleEnum(RoleEnum.INVITED)
						.permisionList(Set.of(readPermission))
						.build();

				RoleEntity roleDeveloper = RoleEntity.builder()
						.roleEnum(RoleEnum.DEVELOPER)
						.permisionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
						.build();

				/* CREATE USERS */
				UserEntity userSantiago = UserEntity.builder()
						.username("santiago")
						.password("1234")
						.isEnabled(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleAdmin))
						.build();

				UserEntity userDaniel = UserEntity.builder()
						.username("daniel")
						.password("1234")
						.isEnabled(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleUser))
						.build();

				UserEntity userAndrea = UserEntity.builder()
						.username("andrea")
						.password("1234")
						.isEnabled(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleInvited))
						.build();

				UserEntity userAnyi = UserEntity.builder()
						.username("anyi")
						.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
						.isEnabled(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleDeveloper))
						.build();

				userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi));
			};

	}



}
