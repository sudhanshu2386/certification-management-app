package com.ibm.mvs.cms.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.mvs.cms.models.Resource;
import com.ibm.mvs.cms.repository.ResourceRepository;

@Service
public class ResourceDetailsServiceImpl implements UserDetailsService {
	@Autowired
	ResourceRepository resourceRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Resource user = resourceRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Resource Not Found with username: " + username));

		return ResourceDetailsImpl.build(user);
	}
}
