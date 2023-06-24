package jaz.s24279.nbp.controller.advice;

import jaz.s24279.nbp.exceptions.CodeNotFound;
import jaz.s24279.nbp.exceptions.InternalServerErrorException;
import jaz.s24279.nbp.exceptions.MissingData;
import jaz.s24279.nbp.exceptions.WrongDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

        @ExceptionHandler(InternalServerErrorException.class)
        public ResponseEntity<Void> handleInternalServerError(InternalServerErrorException ex) {
                return ResponseEntity.status(502).build();
        }

        @ExceptionHandler(CodeNotFound.class)
        public ResponseEntity<String> handleCodeNotFoundException(CodeNotFound codeNotFound) {
                return ResponseEntity.badRequest().body(codeNotFound.getMessage());
        }

        @ExceptionHandler(WrongDate.class)
        public ResponseEntity<String> handleWrongDateException(WrongDate wrongDate) {
                return ResponseEntity.badRequest().body(wrongDate.getMessage());
        }

        @ExceptionHandler(MissingData.class)
        public ResponseEntity<String> handleMissingDataException(MissingData missingData) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(missingData.getMessage());
        }

}
