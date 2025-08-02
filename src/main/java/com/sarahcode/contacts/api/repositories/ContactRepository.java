package com.sarahcode.contacts.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarahcode.contacts.api.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
