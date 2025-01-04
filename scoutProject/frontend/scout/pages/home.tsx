import React from "react";
import apiClient from "@/api/apiClient";
import { useState, useEffect } from "react";
import { Picker } from "@react-native-picker/picker";
import {
    StyleSheet,
    Text,
    View,
    Dimensions,
    TouchableOpacity,
    Modal,
    Button,
    TextInput,
    Alert,
} from "react-native";

interface menuProps {
    onChangeScreen: (screen: string) => void;
}

function Home({ onChangeScreen }: menuProps) {
    const screenHeight = Dimensions.get("window").height;

    const [modalVisible, setModalVisible] = useState(false);
    const [modalContent, setModalContent] = useState("");
    const [text, setText] = useState("");
    const [positions, setPositions] = useState<{ positionId: number; positionDescription: string }[]>([]);
    const [selectedPosition, setSelectedPosition] = useState<number | null>(null);

    const [teamData, setTeamData] = useState({
        teamName: "",
        firstColor: "",
        secondColor: "",
    });

    const [playerData, setPlayerData] = useState({
        playerName: "",
        playerBirthday: "",
        playerWeigth: "",
        playerHeigth: "",
        playerNumber: "",
    });

    const fetchPositions = async () => {
        
        try {
            const response = await apiClient.get("/position");
            setPositions(response.data);
            
        } catch (error) {
            console.error("Erro ao buscar posições:", error);
        }
    };

    useEffect(() => {
        if (modalContent === "Adicionar Jogadores") {
            fetchPositions();
        }
    }, [modalContent]);

    const handleOpenModal = (content: string) => {
        setModalContent(content);
        setModalVisible(true);
    };

    const handleCloseModal = () => {
        setModalVisible(false);
        setText("");
        setTeamData({ teamName: "", firstColor: "", secondColor: "" });
        setPlayerData({ playerName: "", playerBirthday: "", playerWeigth: "", playerHeigth: "", playerNumber: "" });
        setSelectedPosition(null);
    };

    const createChampionship = async (championshipName: string) => {
        try {
            await apiClient.post("/championship", {
                championshipId: null,
                championshipName: championshipName || null,
                qtdTeams: 0,
            });
            Alert.alert("Sucesso", "Campeonato criado com sucesso.");
        } catch (error) {
            console.error("Erro ao criar o campeonato:", error);
        }
    };

    const createTeam = async () => {
        try {
            await apiClient.post("/team", {
                teamId: null,
                ...teamData,
                qtdPlayers: 0,
            });
            Alert.alert("Sucesso", "Time criado com sucesso.");
        } catch (error) {
            console.error("Erro ao criar o time:", error);
        }
    };

    const createPlayer = async () => {
        try {
            await apiClient.post("/player", {
                playerId: null,
                ...playerData,
                position: { positionId: selectedPosition },
            });
            Alert.alert("Sucesso", "Jogador criado com sucesso.");
        } catch (error) {
            console.error("Erro ao criar o jogador:", error);
        }
    };

    return (
        <View style={[styles.container, { height: screenHeight }]}>
            <View style={styles.containerView}>
                <TouchableOpacity onPress={() => handleOpenModal("Adicionar Campeonato")}>
                    <Text style={styles.text}>Adicionar Campeonato</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => handleOpenModal("Adicionar Times")}>
                    <Text style={styles.text}>Adicionar Times</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => handleOpenModal("Adicionar Jogadores")}>
                    <Text style={styles.text}>Adicionar Jogadores</Text>
                </TouchableOpacity>
            </View>

            <Modal
                animationType="fade"
                transparent={true}
                visible={modalVisible}
                onRequestClose={handleCloseModal}
            >
                <View style={styles.modalContainer}>
                    <View style={styles.modal}>
                        <View style={styles.modalHeader}>
                            <Text style={styles.modalText}>{modalContent}</Text>
                            <Button title="X" onPress={handleCloseModal} />
                        </View>
                        <View style={styles.modalView}>
                            {modalContent === "Adicionar Campeonato" && (
                                <>
                                    <Text>Nome do Campeonato:</Text>
                                    <TextInput
                                        placeholder="Digite aqui"
                                        value={text}
                                        onChangeText={(value) => setText(value)}
                                        style={styles.input}
                                    />
                                    <Button
                                        title="Criar campeonato"
                                        onPress={() => {
                                            if (text.trim()) {
                                                createChampionship(text);
                                            } else {
                                                alert("Por favor, insira um nome para o campeonato.");
                                            }
                                        }}
                                    />
                                </>
                            )}
                            {modalContent === "Adicionar Times" && (
                                <>
                                    <Text>Nome do Time:</Text>
                                    <TextInput
                                        placeholder="Digite aqui"
                                        value={teamData.teamName}
                                        onChangeText={(value) => setTeamData({ ...teamData, teamName: value })}
                                        style={styles.input}
                                    />
                                    <Text>Primeira Cor:</Text>
                                    <TextInput
                                        placeholder="Digite aqui"
                                        value={teamData.firstColor}
                                        onChangeText={(value) => setTeamData({ ...teamData, firstColor: value })}
                                        style={styles.input}
                                    />
                                    <Text>Segunda Cor:</Text>
                                    <TextInput
                                        placeholder="Digite aqui"
                                        value={teamData.secondColor}
                                        onChangeText={(value) => setTeamData({ ...teamData, secondColor: value })}
                                        style={styles.input}
                                    />
                                    <Button title="Criar time" onPress={createTeam} />
                                </>
                            )}
                            {modalContent === "Adicionar Jogadores" && (
                                <>
                                    <Text>Nome do Jogador:</Text>
                                    <TextInput
                                        placeholder="Digite aqui"
                                        value={playerData.playerName}
                                        onChangeText={(value) => setPlayerData({ ...playerData, playerName: value })}
                                        style={styles.input}
                                    />
                                    <Text>Data de Nascimento:</Text>
                                    <TextInput
                                        placeholder="DD/MM/AAAA"
                                        value={playerData.playerBirthday}
                                        onChangeText={(value) => setPlayerData({ ...playerData, playerBirthday: value })}
                                        style={styles.input}
                                    />
                                    <Text>Posição:</Text>
                                    <Picker
                                        selectedValue={selectedPosition}
                                        onValueChange={(value) => setSelectedPosition(value)}
                                    >
                                        {positions.map((position) => (
                                            <Picker.Item
                                                key={position.positionId}
                                                label={position.positionDescription}
                                                value={position.positionId}
                                            />
                                        ))}
                                    </Picker>
                                    <Text>Peso:</Text>
                                    <TextInput
                                        placeholder="Digite aqui"
                                        value={playerData.playerWeigth}
                                        onChangeText={(value) => setPlayerData({ ...playerData, playerWeigth: value })}
                                        style={styles.input}
                                    />
                                    <Text>Altura:</Text>
                                    <TextInput
                                        placeholder="Digite aqui"
                                        value={playerData.playerHeigth}
                                        onChangeText={(value) => setPlayerData({ ...playerData, playerHeigth: value })}
                                        style={styles.input}
                                    />
                                    <Text>Número:</Text>
                                    <TextInput
                                        placeholder="Digite aqui"
                                        value={playerData.playerNumber}
                                        onChangeText={(value) => setPlayerData({ ...playerData, playerNumber: value })}
                                        style={styles.input}
                                    />
                                    <Button title="Criar jogador" onPress={createPlayer} />
                                </>
                            )}
                        </View>
                    </View>
                </View>
            </Modal>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        justifyContent: "center",
    },
    containerView: {
        alignItems: "center",
        marginBottom: 80,
    },
    text: {
        margin: 10,
        fontSize: 25,
        color: "#fff",
        fontWeight: "bold",
        backgroundColor: "#1046ec",
        borderRadius: 5,
        padding: 10,
        width: 280,
        textAlign: "center",
    },
    modalContainer: {
        flex: 1,
        justifyContent: "flex-start",
        alignItems: "center",
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        paddingTop: 50,
    },
    modal: {
        backgroundColor: "#fff",
        padding: 20,
        borderRadius: 10,
        width: "90%",
    },
    modalView: {
        marginBottom: 20,
    },
    modalText: {
        fontSize: 18,
        fontWeight: "bold",
        marginBottom: 10,
    },
    modalHeader: {
        flexDirection: "row",
        justifyContent: "space-between",
    },
    input: {
        borderWidth: 1,
        borderColor: "#ccc",
        borderRadius: 5,
        marginBottom: 5,
        padding: 10,
        width: "100%",
    },
});

export default Home;
