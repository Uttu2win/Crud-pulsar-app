package com.personal.crud_pulsar

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>