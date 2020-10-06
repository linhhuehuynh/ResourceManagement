import { Component, Inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
  templateUrl: "./error.component.html",
  selector: "app-error",
})
export class ErrorComponent {
  display=false;
  constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string }) {}
}
