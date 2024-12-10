create database scout;
use scout;

create table statistic(
	statisticId int auto_increment primary key,
    statisticDescription varchar(25) not null
);

insert into statistic (statisticDescription) values
('gol'),
('assistência'),
('passe'),
('passe errado'),
('lançamento'),
('defesa'),
('defesa dificil'),
('finalização certa'),
('finalização errada'),
('desarme'),
('interceptação'),
('frango');

create table position_(
	positionId int auto_increment primary key,
    positionDescription varchar(50)
);

insert into position_(positionDescription) values 
('goleiro'),
('zagueiro'),
('lateral direiro'),
('lateral esquerdo'),
('volante'),
('meio campo'),
('ponta direita'),
('ponta esquerda'),
('atacante'),
('centro-avante');

create table team(
	teamId int auto_increment primary key,
    teamName varchar(70) not null,
    qtdPlayers int not null,
    firstColor varchar(10) not null,
    secondColor varchar(10) not null
);

create table player(
	playerId int auto_increment primary key,
    playerName varchar(55) not null,
    playerBirthday date not null,
    positionId int,
    playerWeigth decimal(5,2) not null,
    playerHeight decimal(5,2) not null,
    playerNumber int not null,
    foreign key (positionId) references position_(positionId)
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
    foreign key (championshipId) references championship(championshipId),
    constraint chk_teams_different check (teamHome <> teamVisit)
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

delimiter //

create trigger add_championship_stats
after insert on match_stats
for each row 
begin 

    declare champ_id int;
    select championshipId
    into champ_id 
    from match_ 
    where matchId = new.matchId;

    insert into championship_stats (championshipId, statisticId) 
    values (champ_id, new.statisticId);
end;
//

delimiter ;

delimiter //

create trigger add_team_status
after insert on match_stats
for each row 
begin 
	 
	declare team_id int;
	select teamId
	into team_id
	from team_player
	where teamPlayerId = new.teamPlayerId;
	
	insert into team_stats(teamId, statisticId)
	values (team_id, new.statisticId);
end;
//

delimiter ;

delimiter //

create trigger add_player_status
after insert on match_stats
for each row 
begin 
	 
	declare player_id int;
	select playerId
	into player_id
	from team_player
	where teamPlayerId = new.teamPlayerId;
	
	insert into player_stats(playerId, statisticId)
	values (player_id, new.statisticId);
end;
//

delimiter ;