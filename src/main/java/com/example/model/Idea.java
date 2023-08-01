package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count" , columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
        @NamedQuery(name = "Idea.findStatus", query = "select i from Idea i where i.status = :status"),
        @NamedQuery(name = "Idea.updateIdeaStatus" , query = "update Idea i set i.status = :status where i.id = :id")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Idea.findStatusNative", query = "select * from idea i where i.status = :status", resultClass = Idea.class),
        @NamedNativeQuery(name = "Idea.findStatusNative.count", query = "select count(*) as cnt from idea i where i.status = :status" , resultSetMapping = "SqlResultSetMapping.count"),
        @NamedNativeQuery(name = "Idea.updateIdeaStatusNative", query = "update idea i set i.status = :status where i.id = :id")

})
public class Idea extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native" , strategy = "native")
    private int id;

    @Column(name= "firstname")
    @NotBlank(message = "First Name must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Invalid First Name")
    private String firstName;

    @Column(name= "lastname")
    @NotBlank(message = "Last Name must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Invalid Last Name")
    private String lastName;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotBlank(message = "Address2 must not be blank")
    private String address2;

    @NotBlank(message = "Idea must not be blank")
    @Size(min=10,message = "Idea must be more descriptive")
    private String opinion;

    private String status;




}
