package ua.edu.ukma.javaee.polishchuk.homework9.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.ukma.javaee.polishchuk.homework9.ValidISBN;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookForm {
    @NotEmpty(message = "You must specify book name.")
    private String Name;
    @NotEmpty(message = "You must specify book author.")
    private String Author;
    @NotEmpty(message = "You must specify ISBN.")
    @ValidISBN(message = "Invalid ISBN format")
    private String Isbn;
}
