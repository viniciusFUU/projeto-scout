import { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import apiCliente from '../api/apiClient';

interface ChampionshipType {
    championshipId: number;
    championshipName: string;
}

function Championship() {
    const [Championship, setChampionship] = useState<ChampionshipType[]>([]);

    useEffect(() => {
        apiCliente.get('/championship')
            .then(response => {
                setChampionship(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar dados: ', error);
            });
    }, []);

    return (
        <View style={styles.container} >
            {Championship.map(champ => (
                <Text style={styles.text} key={champ.championshipId}>{champ.championshipName}</Text>
            ))}
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        alignItems: "center"
    },
    text: {
        backgroundColor: "#1046ec",
        width: 250,
        padding: 10,
        marginTop: 5,
        color: '#fff',
        textAlign: "center",
        borderRadius: 5,
        fontSize: 18
    }
});

export default Championship;
