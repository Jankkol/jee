package com.jankkol.jee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyGroup {
        
        private Long id;
        private String groupname;
        private String username;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }

        public String getGroupname() {
                return groupname;
        }
        public void setGroupname(String groupname) {
                this.groupname = groupname;
        }
        
        public String getUsername() {
                return username;
        }
        public void setUsername(String username) {
                this.username = username;
        }

}