package com.cognizant.claimservice;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.cognizant.claimservice.model.Claims;
import com.cognizant.claimservice.repository.ClaimRepo;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ClaimServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimServiceApplication.class, args);
	}
	
	@Bean
	public FeignContext getFeignContext()
	{
		return new FeignContext();

	}
	
	@Bean
	public FeignClientProperties getFeignClientProperties()
	{
		return new FeignClientProperties();

	}

	@Bean
	public ClaimRepo getClaimRepo() {
		return new ClaimRepo() {

			@Override
			public <S extends Claims> Optional<S> findOne(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Claims> Page<S> findAll(Example<S> example, Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Claims> boolean exists(Example<S> example) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public <S extends Claims> long count(Example<S> example) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public <S extends Claims> S save(S entity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Optional<Claims> findById(Integer id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean existsById(Integer id) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void deleteById(Integer id) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAll(Iterable<? extends Claims> entities) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAll() {
				// TODO Auto-generated method stub

			}

			@Override
			public void delete(Claims entity) {
				// TODO Auto-generated method stub

			}

			@Override
			public long count() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Page<Claims> findAll(Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Claims> S saveAndFlush(S entity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Claims> List<S> saveAll(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Claims getOne(Integer id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void flush() {
				// TODO Auto-generated method stub

			}

			@Override
			public List<Claims> findAllById(Iterable<Integer> ids) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Claims> List<S> findAll(Example<S> example, Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Claims> List<S> findAll(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Claims> findAll(Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Claims> findAll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void deleteInBatch(Iterable<Claims> entities) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAllInBatch() {
				// TODO Auto-generated method stub

			}

			@Override
			public List<Claims> findByMemberId(int memberId) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Claims findByClaimNumberAndMemberIdAndPolicyNumber(int claimNumber, int memberId, int policyNumber) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	

}
