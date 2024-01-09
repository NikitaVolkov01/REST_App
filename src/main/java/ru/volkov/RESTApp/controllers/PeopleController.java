package ru.volkov.RESTApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.volkov.RESTApp.models.Person;
import ru.volkov.RESTApp.service.PeopleService;
import ru.volkov.RESTApp.util.PersonErrorResponse;
import ru.volkov.RESTApp.util.PersonNotCreatedException;
import ru.volkov.RESTApp.util.PersonNotFoundException;

import java.util.List;
@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getPeople() {
        return peopleService.findAll(); // Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id); // Jackson конвертирует в JSON
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
                                             BindingResult bindingResult) { //метод с помощью ResponseEntity<HttpStatus> возвращает Http ответ
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new PersonNotCreatedException(errorMsg.toString());
        }
        peopleService.save(person);
        //отправляем HTTP ответ с пустым телом и статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler //такой аннотацией помечаем тот метод, который в себя ловит исключение и возвращает необходимый JSON
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) { //в параметрах метода указываем какое исключение будем ловить
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //NOT_FOUND - 404 статус
    }

    @ExceptionHandler //такой аннотацией помечаем тот метод, который в себя ловит исключение и возвращает необходимый JSON
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) { //в параметрах метода указываем какое исключение будем ловить
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //400 статус
    }
}