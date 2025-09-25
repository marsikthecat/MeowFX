package meow.meowfx.snippets;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import meow.meowfx.forms.Button;
import meow.meowfx.forms.Input;
import meow.meowfx.forms.LabeledInput;
import meow.meowfx.forms.PasswordInput;
import meow.meowfx.text.P;
import meow.meowfx.internals.Element;

public class LoginForm extends Element<VBox> {

  private final P usernameLabel;
  private final Input usernameInput;
  private final P passwordLabel;
  private final PasswordInput passwordInput;
  private final Button loginButton;

  public LoginForm() {
    super(new VBox());
    usernameLabel = new P("Username:");
    usernameInput = new Input();
    LabeledInput userNameLine = new LabeledInput(usernameLabel, usernameInput);
    passwordLabel = new P("Password:");
    passwordInput = new PasswordInput();
    HBox passwordLine = new HBox(passwordLabel, passwordInput);
    loginButton = new Button("Login");
    getNode().getChildren().addAll(userNameLine, passwordLine, loginButton);
  }

  public void usernameLabel(String label) {
    usernameLabel.text(label);
  }

  public void passwordLabel(String label) {
    passwordLabel.text(label);
  }

  public void loginButtonText(String text) {
    loginButton.text(text);
  }

  public String getUsername() {
    return usernameInput.getValue();
  }

  public String getPassword() {
    return passwordInput.getPassword();
  }

  public void onSubmit(Runnable runnable) {
    loginButton.onclick(runnable);
  }
}