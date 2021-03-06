package me.studyolle.clone.account;

import lombok.RequiredArgsConstructor;
import me.studyolle.clone.domain.Account;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private static final String SIGN_UP_PAGE_PATH = "account/sign-up";

    private final SignUpFormValidator signUpFormValidator;
    private final AccountRepository accountRepository;
    private final JavaMailSender javaMailSender;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new SignUpForm());
        return SIGN_UP_PAGE_PATH;
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return SIGN_UP_PAGE_PATH;
        }

        final Account account = Account.builder()
                                       .email(signUpForm.getEmail())
                                       .nickname(signUpForm.getNickname())
                                       .password(signUpForm.getPassword()) // TODO 인코딩
                                       .isCreatedNotifiedByWeb(true)
                                       .isStudyUpdatedByWeb(true)
                                       .isEnrollmentResultByWeb(true)
                                       .build();

        final Account newAccount = accountRepository.save(account);
        newAccount.generateEmailCheckToken();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newAccount.getEmail());
        mailMessage.setSubject("스터디올래클론, 회원가입 인증");
        mailMessage.setText("/check-email-token?token=" + newAccount.getEmailCheckToken() + "&email=" + newAccount.getEmail());
        javaMailSender.send(mailMessage);
        return "redirect:/";
    }
}
