import { useState } from "react";
import {
    StyleSheet,
    Text,
    View,
    Dimensions,
    TouchableOpacity,
    Modal,
    Button,
    TextInput,
} from "react-native";

interface menuProps {
    onChangeScreen: (screen: string) => void;
}

function Home({ onChangeScreen }: menuProps) {
    const screenHeight = Dimensions.get("window").height;

    const [modalVisible, setModalVisible] = useState(false);
    const [modalContent, setModalContent] = useState("");
    const [text, setText] = useState("");

    const handleOpenModal = (content: string) => {
        setModalContent(content);
        setModalVisible(true);
    };

    const handleCloseModal = () => {
        setModalVisible(false);
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
                animationType="slide"
                transparent={true}
                visible={modalVisible}
                onRequestClose={handleCloseModal}
            >
                <View style={styles.modalContainer}>
                    <View style={styles.modal}>
                        <Text style={styles.modalText}>{modalContent}</Text>
                        <View style={styles.modalView}>
                            <Text>Nome do time:</Text>
                            <TextInput
                                placeholder="Digite aqui"
                                value={text}
                                onChangeText={(value) => setText(value)}
                                style={styles.input}
                            />
                        </View>
                        <Button title="Fechar" onPress={handleCloseModal} />
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
        justifyContent: "flex-start", // Alinha o conteúdo no topo
        alignItems: "center",
        backgroundColor: "rgba(0, 0, 0, 0.5)",
        paddingTop: 50, // Adiciona um espaço no topo
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
        textAlign: "center",
    },
    input: {
        borderWidth: 1,
        borderColor: "#ccc",
        borderRadius: 5,
        padding: 10,
        width: "100%",
    },
});

export default Home;
