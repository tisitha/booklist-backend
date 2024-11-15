package booklist.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BOOK")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Double price;
    private Integer publish_year;
    private Long userId;

}
