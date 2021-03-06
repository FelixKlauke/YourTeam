package de.dercoder.yourteam.deploy;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.inject.Provides;
import com.google.inject.name.Named;

import de.dercoder.yourteam.core.TeamModule;
import javax.inject.Singleton;

public final class TeamDeployModule extends TeamModule {
  private final Path configurationFilePath;

  private TeamDeployModule(
    Path userFilePath,
    Path memberFilePath,
    Path historyFilePath,
    Path groupFilePath,
    Path configurationFilePath
  ) {
    super(userFilePath, memberFilePath, historyFilePath, groupFilePath);
    this.configurationFilePath = configurationFilePath;
  }

  @Provides
  @Singleton
  @Named("configurationFilePath")
  Path provideConfigurationFilePath() {
    return configurationFilePath;
  }

  public static TeamDeployModule create() {
    var applicationPath = Paths.get("").toAbsolutePath().toString();
    var userFilePath = Path.of(applicationPath + "/user/users.yml");
    var memberFilePath = Path.of(applicationPath + "/member/members.yml");
    var historyFilePath = Path.of(applicationPath + "/member/history.yml");
    var groupFilePath = Path.of(applicationPath + "/group/groups.yml");
    var configurationFilePath = Path.of(applicationPath + "/configuration" + "/configuration.yml");
    return new TeamDeployModule(
      userFilePath,
      memberFilePath,
      historyFilePath,
      groupFilePath,
      configurationFilePath
    );
  }
}
