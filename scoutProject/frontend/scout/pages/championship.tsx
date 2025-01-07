import { useEffect, useState } from "react";
import { Modal, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import apiCliente from '../api/apiClient';

interface ChampionshipType {
    championshipId: number;
    championshipName: string;
}

interface TeamType {
    teamId: number;
    teamName: string;
}

interface menuProps {
    onChangeScreen: (screen: string) => void;
}

function Championship({ onChangeScreen }: menuProps) {
    const [championship, setChampionship] = useState<ChampionshipType[]>([]);
    const [teams, setTeams] = useState<TeamType[]>([]);
    const [modalVisible, setModalVisible] = useState(false);
    const [modalContent, setModalContent] = useState("");

    useEffect(() => {
        apiCliente.get('/championship')
            .then(response => {
                setChampionship(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar dados de campeonato: ', error);
            });

        apiCliente.get('/team')
            .then(response => {
                setTeams(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar dados de times: ', error);
            });
    }, []);

    const handleOpenModal = (content: string) => {
        setModalContent(content);
        setModalVisible(true);
    };

    const handleCloseModal = () => {
        setModalVisible(false);
    };

    return (
        <View style={styles.container}>
            <View>
                {championship.map(champ => (
                    <TouchableOpacity
                        key={champ.championshipId}
                        onPress={() => handleOpenModal(champ.championshipName)}
                    >
                        <Text style={styles.text}>{champ.championshipName}</Text>
                    </TouchableOpacity>
                ))}
            </View>

            <Modal
                animationType="fade"
                transparent={true}
                visible={modalVisible}
                onRequestClose={handleCloseModal}
            >
                <View style={styles.modalContainer}>
                    <Text style={styles.modalTitle}>Campeonato: {modalContent}</Text>
                    <Text style={styles.modalSubTitle}>Times:</Text>
                    {teams.map(team => (
                        <Text key={team.teamId} style={styles.teamText}>
                            {team.teamName}
                        </Text>
                    ))}
                    <TouchableOpacity onPress={handleCloseModal}>
                        <Text style={styles.closeButton}>Fechar</Text>
                    </TouchableOpacity>
                </View>
            </Modal>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        alignItems: "center",
    },
    text: {
        backgroundColor: "#1046ec",
        width: 250,
        padding: 10,
        marginTop: 5,
        color: "#fff",
        textAlign: "center",
        borderRadius: 5,
        fontSize: 18,
    },
    modalContainer: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        padding: 20,
    },
    modalTitle: {
        fontSize: 22,
        fontWeight: "bold",
        marginBottom: 10,
        color: "#fff",
    },
    modalSubTitle: {
        fontSize: 18,
        marginBottom: 5,
        color: "#fff",
    },
    teamText: {
        fontSize: 16,
        color: "#fff",
        marginBottom: 2,
    },
    closeButton: {
        marginTop: 20,
        backgroundColor: "#1046ec",
        color: "#fff",
        padding: 10,
        borderRadius: 5,
        textAlign: "center",
    },
});

export default Championship;
