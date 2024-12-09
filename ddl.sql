create database scout;
use scout;

create table statistic(
	statisticId int auto_increment primary key,
    statisticDescription varchar(25) not null
);

create table team(
	teamId int auto_increment primary key,
    teamName varchar(70) not null,
    qtdPlayers int not null,
    firstColor varchar(10),
    secondColor varchar(10)
);

create table player(
	playerId int auto_increment primary key,
    playerName varchar(55),
    playerBirthday date,
    playerWeigth decimal(3,2),
    playerHeight decimal(3,2),
    playerNumber int not null
);

create table team_player(
	teamPlayerId int auto_increment primary key,
    teamId int,
    playerId int,
    foreign key (teamId) references team(teamId),
    foreign key (playerId) references player(playerId)
);

create table championship(
	championshipId int auto_increment primary key,
    championshipName varchar(50) not null,
    qtdTeams int not null
);

create table team_championship(
	teamChampionshipId int auto_increment primary key,
	teamId int,
    championshipId int,
    qtdInserido int,
    foreign key (teamId) references team(teamId),
    foreign key (championshipId) references championship(championshipId)
);

create table match_(
	matchId int auto_increment primary key,
    championshipId int,
    teamHome int,
    teamVisit int,
    foreign key (teamHome) references team(teamId),
    foreign key (teamVisit) references team(teamId),
    foreign key (championshipId) references championship(championshipId)
);

create table match_stats(
	matchStatsId int auto_increment primary key,
    matchId int,
    teamPlayerId int,
	teamChampionshipId int,
    statisticId int,
    foreign key (matchId) references match_(matchId),
    foreign key (teamPlayerId) references team_player(teamPlayerId),
    foreign key (teamChampionshipId) references team_championship(teamChampionshipId),
    foreign key (statisticId) references statistic(statisticId)
);

create table championship_stats(
	championshipStatsId int auto_increment primary key,
    championshipId int,
    statisticId int, 
    foreign key (championshipId) references championship(championshipId),
    foreign key (statisticId) references statistic(statisticId)
);

create table team_stats(
	teamStatsId int auto_increment primary key,
    teamId int,
    statisticId int,
    foreign key (teamId) references team(teamId),
    foreign key (statisticId) references statistic(statisticId)
);

create table player_stats(
	playerStatsId int auto_increment primary key,
    playerId int,
    statisticId int,
    foreign key (playerId) references player(playerId),
    foreign key (statisticId) references statistic(statisticId)
);