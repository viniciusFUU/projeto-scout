import { StyleSheet, Text, View, Dimensions, TouchableOpacity } from "react-native";

interface menuProps{
    onChangeScreen: (screen: string) => void;
}

function Home({onChangeScreen}: menuProps) {
    const screenHeight = Dimensions.get("window").height;

    return (
        <View style={[styles.container, { height: screenHeight }]}>
            <View style={styles.containerView}>
                <TouchableOpacity>
                    <Text style={styles.text}>Adicionar Campeonato</Text>
                </TouchableOpacity>
                <TouchableOpacity>
                    <Text style={styles.text}>Adicionar Times</Text>
                </TouchableOpacity>
                <TouchableOpacity>
                    <Text style={styles.text}>Adicionar Jogadores</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        justifyContent: "center",
    },
    containerView: {
        alignItems:"center",
        marginBottom: 80
    },
    text: {
        margin: 10,
        fontSize: 25,
        color: '#fff',
        fontWeight: "bold",
        backgroundColor: '#1046ec',
        borderRadius: 5,
        padding: 10,
        width: 280,
        textAlign: "center"
    }
});

export default Home;
