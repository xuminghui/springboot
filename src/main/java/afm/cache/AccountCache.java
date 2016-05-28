package afm.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import afm.entities.Account;
import afm.repositories.AccountRepository;

/**
 * Created by rchen on 3/23/16.
 */
@Service
public class AccountCache {

    @Autowired
    private AccountRepository repository;

   @CachePut(value=CachingConfig.ACCOUNTS_CACHE,cacheManager = "cacheManager", keyGenerator = "accountKeyGenerator")
   // @Cacheable(cacheManager = "cacheManager", keyGenerator = "accountKeyGenerator")
    public Account getAccount(Integer id) {
    	Account account=repository.findOne(id);
        return account;
    }

    @CacheEvict(cacheManager = "cacheManager", keyGenerator = "accountKeyGenerator")
    public void evictAccountCache(Integer id) {

    }

    public Account saveAccount(Account account) {
        account = repository.save(account);
        evictAccountCache(account.getId());
        return getAccount(account.getId());
    }

    public Account deleteAccount(Account account) {
        repository.delete(account);
        evictAccountCache(account.getId());
        return account;
    }

    public Account updateAccount(Account newAccount) {
        Account account = repository.save(newAccount);
        evictAccountCache(account.getId());
        return getAccount(account.getId());
    }
    @Cacheable(value=CachingConfig.ACCOUNTS_CACHE,keyGenerator="accountKeyGenerator",cacheManager="cacheManager")
    public Account testAccount(String id){
    	return new Account();
    }

}
