package com.resiliencecc.web.security;

import com.resiliencecc.api.constraint.Role;
import com.resiliencecc.api.exception.DataNotFoundException;
import com.resiliencecc.api.manager.CollectionManager;
import com.resiliencecc.api.model.UserEntity;
import com.resiliencecc.api.model.UserEntity_;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.PasswordHash;

@ApplicationScoped
public class AuthenticationProvider implements IdentityStore {

    @EJB
    private CollectionManager collectionManager;

    @Inject
    private PasswordHash passwordHash;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
        String phoneOrEmail = usernamePasswordCredential.getCaller();
        Password password = usernamePasswordCredential.getPassword();

        try {
            UserEntity userEntity = collectionManager.findAny(UserEntity.class,
                    Map.of(UserEntity_.CELL_PHONE, phoneOrEmail,
                            UserEntity_.EMAIL_ADDRESS, phoneOrEmail));

            if (passwordHash.verify(password.getValue(), userEntity.getPassword())) {
                CallerPrincipal principal = new CallerPrincipal(userEntity.getUserId());
                Set<String> roles = userEntity.getRoles().stream().map(Role::name).collect(Collectors.toSet());
                return new CredentialValidationResult(principal, roles);
            } else {
                return CredentialValidationResult.INVALID_RESULT;
            }
        } catch (DataNotFoundException ex) {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }

}
