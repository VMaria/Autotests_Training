package mantis.model;


import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

    @XStreamAlias("account")
    @Entity
    @Table(name = "mantis_user_table")
    public class AccountData {

        @XStreamOmitField
        @Id
        @Column(name = "id")
        private int id = Integer.MAX_VALUE;

        @Expose
        @Column(name = "username")
        private String name;

        @Expose
        @Column(name = "email")
        //@Type(type = "varchar")
        private String email;

        @Expose
        @Column(name = "password")
        //@Type(type = "text")
        private String password;

        public String getName() {

            return name;
        }

        public String getEmail() {

            return email;
        }

        public String getPassword() {

            return password;
        }

        public int getId() {

            return id;
        }

        public AccountData withId(int id) {

            this.id = id;
            return this;
        }

        public AccountData withName(String name) {
            this.name = name;
            return this;
        }


        public AccountData withEmail(String email) {
            this.email = email;
            return this;
        }

        public AccountData withPassword(String password) {
            this.password = password;
            return this;
        }
    }
