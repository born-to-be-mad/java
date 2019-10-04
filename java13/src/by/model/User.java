package by.model;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String surname;
	
	@Column(nullable = false)
    private Integer age;
    
    // getters and setters omitted
}
