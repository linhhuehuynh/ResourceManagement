import { Directive } from "@angular/core";
import {
  AbstractControl,
  Validator,
  NG_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { passwordValidator } from "./password-validate";

@Directive({
  selector: "[appPassword]",
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: PasswordValidatorDirective,
      multi: true
    }
  ]
})

export class PasswordValidatorDirective implements Validator {
  validate(control: AbstractControl): ValidationErrors {
    return passwordValidator(control);
  }
}