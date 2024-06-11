package ru.edmvl.tgbot.statBot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edmvl.tgbot.statBot.entity.Log;

@Repository
public interface LogRepo extends JpaRepository<Log, Long> {

}
