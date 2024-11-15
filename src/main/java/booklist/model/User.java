package booklist.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "READER")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
